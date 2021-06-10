export interface AttributeModel {
    id: number

    name: string;

    selectType: "single" | 'multiple'; // single, multiple

    entryMethod: 'custom' | 'selection'; // custom, selection

    categoryId: number;
    searchtype: 'color' | 'normal'
    sort: number;

    searchable: boolean;

    value: string;


}