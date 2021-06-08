// private Integer id;
// private String name;
// private Integer parent;

export interface CategoryModel {
    id: number,
    name: string,
    parent: number,
    level: number,
    isleaf: boolean;
    visible: boolean;
    priority: number;
    suffix: string;
    keyword: string;
}

export interface CategoryQueryVO {
    parent?: number
};
