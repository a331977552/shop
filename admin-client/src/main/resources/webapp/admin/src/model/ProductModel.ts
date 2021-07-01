
export interface ProductModel {
    id: string;

    name: string;

    categoryId: number;

    brandId: number;

    weight?: number;

    sales?: number;

    status: "OUT_OF_ORDER" | "ON_SALE";

    priority: number;

    thumbnailImg: string;

    standardImg?: string;

    subtitle:string;

    description?:string;

    itemNo?:string;

    price?:number;

    marketPrice?:number;

    detail?:string,

    specs?:string,
    skuList?: Sku[]

    suffix?:string //计量单位

}

export type Sku = {
    stock: string, price: string, img: string | undefined, attribute: string
}

export interface ProductQueryModel {
    categoryId: number;
    name: string;
    status: "OUT_OF_ORDER" | "ON_SALE";
    brandId:number,
}