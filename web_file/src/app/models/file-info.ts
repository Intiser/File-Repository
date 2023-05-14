import { User } from "./user";

export interface FileInfo{
    id?:number
    name?: string;
    size?: number;
    user?:User;
    downloadLink?:string;
    date?:string;
    blocked?:boolean;
    dateTime?:Date;
    fileType?:string;
}