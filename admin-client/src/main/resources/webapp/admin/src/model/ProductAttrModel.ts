export interface ProductAttrModel {
    id: number

    name: string;

    selectType: "single" | 'multiple'; // single, multiple

    entryMethod: 'custom' | 'selection'; // custom, selection

    categoryId: number;

    searchtype: 'color' | 'normal'

    sort: number;

    searchable: boolean;

    value: string;

    values:Array<ProductAttrValueModel>;

}
export interface ProductAttrValueModel{
    value:string
    id?:number,
    attrKey?:number,
}
export interface ProductAttrQueryModel{

    name?: string;
    categoryId?: number;
}