export interface SearchRequest {
    pageSize: number;
    pageIndex: number;
    sortDir: SortDirection;
    sortName: String;
}

export enum SortDirection {
    ASC = "ASC",
    DESC = "DESC",
}