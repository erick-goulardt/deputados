export interface IDeputado {
    id: number
    name: string,
    urlPhoto: string,
    acronymParty: string
}

export interface IEvento {
    id: number,
    descType: string
}

export interface IDefineEvento {
    idDeputy: number,
    idEvent: number
}

export interface IListEvento {
    id: number,
    description: string,
    dateInicio: Date,
    situation: string,
    descpType: string
}