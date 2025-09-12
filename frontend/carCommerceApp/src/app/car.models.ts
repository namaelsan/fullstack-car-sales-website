export interface Car {
    id: number;
    brand: Brand;
    specification: string;
    litre: number;
    price: number;
    releaseDateTime: Date;
    used: boolean;
}

export interface Brand {
    id?: number;
    bname: string;
    cars?: Car[];
}

export interface Image {
    id: number;
    filename: string;
    fullPath: string;
}