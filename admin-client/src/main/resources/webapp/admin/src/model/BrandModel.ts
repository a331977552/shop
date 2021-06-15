export interface BrandModel {
    id: number
    name: string;
    capitalLetter:string;
    priority:number;
    isManufacturer:boolean;
    visible:boolean;
    info:string;
    description:string;
}

export interface BrandQueryModel{

    name?: string;
}