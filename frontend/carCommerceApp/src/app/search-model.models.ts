import { SearchRequest } from "./search-request.models";

export interface SearchModel<T> {
    searchRequest: SearchRequest;
    model: T;
}
