
export  type  SkuImg = {id?:string,key?:string,loading:boolean};
export  type  SkuVal =  string|SkuImg;
export  type  SkuObject =  {id:string,key?:string,loading:boolean};
export  type  KeyStr = { [key: string]: string };
export  type  SkuKeyVal = { [key: string]: SkuVal};
export  type  KeyVals = { [key: string]: string[] };
export  type  KeyValMix = { [key: string]: string | string[] };
export  type  Status = "loading" | "error" | "finished";

export  type  RouterState = {updateMenu?:boolean};


