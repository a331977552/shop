import {KeyValMix, KeyVals, ProductModel, Sku, SkuImg, SkuKeyVal} from "../../model";


export function  transformSkuListToRequestData(skuList:SkuKeyVal[]|undefined){
    return (skuList || []).map(constSku=>{
        let sku = {...constSku};
        delete sku["stock"];
        delete sku["price"];
        delete sku["img"];
        return {stock:String(constSku["stock"]),price:String(constSku["price"]),img:(constSku["img"]as SkuImg).id,attribute:JSON.stringify(sku)}
    })
}

export function assembleProductRequestData(productModel:ProductModel,detailHtml:string,specsRequestData:any,skuRequestData:Sku[]):ProductModel{
    return {...productModel,detail:detailHtml,specs:JSON.stringify(specsRequestData),skuList:skuRequestData}
}

export function generateData(valueMap: KeyValMix): Array<SkuKeyVal> {
    const data: SkuKeyVal[] = [];
    if (Object.keys(valueMap).length === 0)
        return data;

    function _generateData(keys: string[], valueMap: KeyValMix, obj: any = {}) {
        const key = keys[0];
        let valArrayOfKey = valueMap[key];
        if (typeof valArrayOfKey === 'string') {
            valArrayOfKey = valArrayOfKey.split("\n");
        }
        valArrayOfKey.forEach(val => {
            if (keys.length === 1) {
                data.push({...obj, [key]: val, price: '', stock: '', img: {}});
            } else {
                _generateData(keys.slice(1), valueMap, {...obj, [key]: val})
            }
        });
    }

    _generateData(Object.keys(valueMap), valueMap, {});
    return data;
}


export function transformSkuListToForm(initAttrValues: { stock: string, price: string, img: string | undefined, attribute: string }[] | undefined):KeyVals| {  } {
    return initAttrValues?initAttrValues.reduce((accu: any, current) => {
        const attributes = JSON.parse(current.attribute);//{规格:['1'],颜色:[2]}
        Object.keys(attributes).forEach(attrKey => {
            if (!accu[attrKey]) {
                accu[attrKey] = [];
            }
            if (!accu[attrKey].some((val: any) => attributes[attrKey] === val)) {
                accu[attrKey].push(attributes[attrKey]);
            }
        });
        return accu;
    },{}):{}
}

export type  TableItem = {
    title: string,
    dataIndex: string
};
export const columnsPart2: TableItem[] =
    [
        {
            title: '价格',
            dataIndex: 'price',
        },
        {
            title: '库存',
            dataIndex: 'stock',
        },
        {
            title: '图片',
            dataIndex: 'img',
        }
    ]

