export interface datosVacante{
    estado: string;
    evaluacion: [{
        id: number,
        nombre: string
    }];
    evc: string;
    evento: string;
    fechaCierre: string;
    fechaPostulado?: string;
    fechaPropuesta?: string;
    fechaReal?: string;
    fechaResultado?: string;
    fechaSolicitud: string;
    habilidades: [{
        id: number,
        nombre: string,
    }];
    id?: number;
    medellin: boolean;
    observaciones: string;
    perfil: string;
    rgs: string;
    tipoSolicitud: string;
}