import { FileInfo } from "./file-info";

export interface RequestInfo{
    id?:number;
    file?:FileInfo;
    title?:string;
    description?:string;
    isResolved?:boolean;
}