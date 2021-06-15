import {CategoryModel} from "../../model";

export interface CategoryTree {
    value: string,
    title: string,
    children?: CategoryTree[];
}

function transformData(parent: CategoryModel,ignore?:number) {
    return (parent.children || []).filter(item=>item.id !== ignore).map((child, index) => {
        const parent = {value: child.id + "", title: child.name} as CategoryTree;
        parent.children = transformData(child,ignore);
        return parent;
    })
}

export function findCategoryByID(items:CategoryModel[],id:number) {
    return  items.find(item =>
        item.id === id );
}

export function convertToUITree(items?: CategoryModel[], ignore?:number) {
    let topParent = (items?.filter(item => item.parent === 0 && item.id !== ignore)) || [];
    const syntheticParents = {
        children: topParent, id: 0, name: "", parent: 0, level: 0,
        navVisible:true,
        isleaf: false, visible: false, priority: 0, suffix: "", keyword: ""
    }
    const topLevelTree =   transformData(syntheticParents,ignore);
    topLevelTree.unshift({value: '0', title: '无父分类'})
    return topLevelTree;
};

export const convertToTreeCategory = (parent: CategoryModel, dataSource: Array<CategoryModel>) => {
    const children = dataSource.filter(item => item.parent === parent.id);
    parent.children = children;
    children.forEach(parent => {
        convertToTreeCategory(parent, dataSource);
    })
}

export function removeCategory(items:CategoryModel[],removeId:number) {
        return items?.filter(item =>
        item.id !== removeId) as CategoryModel[];
}
export function findCategoriesByParentID(items:CategoryModel[],parentID:number) {
        return items.filter(item =>
        item.parent === parentID );
}

export function resetToLeafIfNecessary(items:CategoryModel[],parentId:number) {
    const elementsWithSameParent = items.reduce((acc, curr) =>
            acc + (curr.parent === parentId ? 1 : 0)
        , 0)
    //sync parent leaf status
    console.log(items,parentId,elementsWithSameParent);
    if (elementsWithSameParent === 0) {
        let parent = items.find(item => item.id === parentId) as CategoryModel;
        parent.isleaf = true;
    }
}

export function removeTreeNode(items:CategoryTree[],removeID:string) {

    function removeTreeNode_(parent:CategoryTree,removeID:string){
        const children = parent.children||[];
        const filteredNodes = children.filter(item=>item.value !== removeID);
        if (filteredNodes.length !== children.length){
            parent.children = filteredNodes;
        }else{
            children.forEach((parent)=>{
                removeTreeNode_(parent,removeID);
            })
        }
    }

    const syntheticParents ={
        children: items,
        title: '',
        value:""
    }
    removeTreeNode_(syntheticParents,removeID);
     return syntheticParents.children;
}
