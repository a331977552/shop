export interface ProductModel {
    id: string;

    name: string;

    category: number;

    brand: number;

    weight?: number;

    sales?: number;

    status: "OUT_OF_ORDER" | "ON_SALE";

    priority: number;

    thumbnailImg: string;

    standardImg: string;

}

export interface ProductQueryModel {
    category: number;
    name: string;
    status: "OUT_OF_ORDER" | "ON_SALE";
    brand:number,
}