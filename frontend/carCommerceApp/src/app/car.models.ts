export interface Car {
    id: number;
    brand: Brand;
    specification: string;
    litre: number;
    price: number;
    releaseDateTime: Date;
}

export interface Brand {
    id: number;
    bname: string;
}

export interface Image {
    id: number;
    filename: string;
    fullPath: string;
}