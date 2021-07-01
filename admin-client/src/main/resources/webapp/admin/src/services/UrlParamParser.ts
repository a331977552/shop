import qs from 'qs';

export function parseSearchParams(params:string){
    return  qs.parse(params, {ignoreQueryPrefix: true});
}

