export default interface ResultModel<T> {
    timestamp: Date;
    code: number;
    msg: string;
    msgDetail?: string;
    result?: T;
}
