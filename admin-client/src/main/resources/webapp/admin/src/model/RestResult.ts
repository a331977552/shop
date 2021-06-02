export default interface RestResult<T> {
    timestamp: Date;
    code: number;
    msg: string;
    msgDetail?: string;
    result?: T;
}