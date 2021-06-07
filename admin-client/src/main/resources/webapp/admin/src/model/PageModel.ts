export interface PageModel<Item>{
    currentPage:number;
    pageSize: number;
        items:Array<Item>;
    totalPages:number;
    totalElements:number;
    isEmpty:boolean;
    first:boolean;
    last:boolean;
    offset:number;
    orderBy:string;

}

export interface PageQueryModel<Example>{
    currentPage:number;
    pageSize: number;
    orderBy?:string;
    example?:Example
}
