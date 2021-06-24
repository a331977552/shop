
export interface CategoryModel {
    id: number,
    name: string,
    parent: number,
    level: number,
    isleaf: boolean;
    visible: boolean;
    navVisible: boolean;
    description?: string;
    priority: number;
    suffix: string;
    keyword: string;
    children?: Array<CategoryModel>
}

export interface CategoryQueryVO {
    parent?: number
};
