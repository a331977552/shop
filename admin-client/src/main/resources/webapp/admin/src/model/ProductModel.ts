import {Simulate} from "react-dom/test-utils";
import keyUp = Simulate.keyUp;

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

    standardImg?: string;

    subtitle:string;

    description?:string;

    itemNo?:string;

    price?:number;

    marketPrice?:number;

    suffix?:string //计量单位

    specs?:{[key:string]:string}
    // attributes:Array<KeyValueModel>

}

export interface ProductQueryModel {
    category: number;
    name: string;
    status: "OUT_OF_ORDER" | "ON_SALE";
    brand:number,
}