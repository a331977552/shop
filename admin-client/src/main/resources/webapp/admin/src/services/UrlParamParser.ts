import qs from 'qs';

export function paramParser(params:string){
    let result = qs.parse(params, { ignoreQueryPrefix: true });
    return  result;
}

