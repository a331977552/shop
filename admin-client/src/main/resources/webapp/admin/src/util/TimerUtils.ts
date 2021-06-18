let timeID:any ;
export const debounce = (fuc:()=>void, time:number)=>{
    function execute(fuc:()=>void, time:number){
        timeID = setTimeout(fuc,time);
    }
    clearTimeout(timeID);
    execute(fuc,time);
}