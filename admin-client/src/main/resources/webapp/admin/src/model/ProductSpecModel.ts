export interface ProductSpecModel{
    id: number

    name: string;

    selectType: "single" | 'multiple'; // single, multiple

    entryMethod: 'custom' | 'selection'; // custom, selection

    categoryId: number;

    sort: number;

    searchable: boolean;

    value: string;
}
export interface ProductSpecQueryModel{

    name?: string;
    categoryId?: number;
}