import {CategoryModel} from "../../model";

export interface CategoryTree {
    value: string,
    title: string,
    children?: CategoryTree[];
}

function transformData(parent: CategoryModel,ignore?:number) {
    return (parent.children || []).filter(item=>item.id !== ignore).map((child, index) => {
        const parent = {value: child.id + "", title: child.name} as CategoryTree;
        parent.children = transformData(child);
        return parent;
    })
}

export function convertToTreeStyle(items?: CategoryModel[],ignore?:number) {
    let topParent = (items?.filter(item => item.parent === 0 && item.id !== ignore)) || [];
    const syntheticParents = {
        children: topParent, id: 0, name: "", parent: 0, level: 0,
        isleaf: false, visible: false, priority: 0, suffix: "", keyword: ""
    }
    const topLevelTree =   transformData(syntheticParents,ignore);
    topLevelTree.unshift({value: '0', title: '一级种类'})
    return topLevelTree;
};