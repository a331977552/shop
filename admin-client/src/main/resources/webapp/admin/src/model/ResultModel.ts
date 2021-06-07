export default interface ResultModel<T> {
    timestamp: string;
    code: number;
    msg: string;
    msgDetail?: string;
    result?: T;
}
