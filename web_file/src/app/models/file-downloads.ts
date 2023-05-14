import { User } from "./user";

export interface FileDownloadInfo{
    id?:number
    name?: string;
    size?: number;
    user?:User;
    downloadLink?:string;
    date?:string;
    isBlocked?:boolean;
    dateTime?:Date;
    data:any;
    fileType?:string;
}