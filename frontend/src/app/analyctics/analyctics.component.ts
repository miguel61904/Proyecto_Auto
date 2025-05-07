import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartType, scales } from 'chart.js';
import { ChartOptions } from 'chart.js';
import { Chart } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { SolicitudesService } from '../services/solicitudes.service';

@Component({
  selector: 'app-analyctics',
  templateUrl: './analyctics.component.html',
  styleUrl: './analyctics.component.css'
})
export class AnalycticsComponent  {
  @ViewChild(BaseChartDirective) chart!: BaseChartDirective;
  fechaInicial = "";
  fechaFinal = "";
  pressButton = false;

  
  constructor(private solicitudesService: SolicitudesService) { }

  ngOnInit(): void {
    this.cargarDatos();
    this.cargarDatosDos("fechaSolicitud", "", "");
    this.cargarDatosTres();
  }
//GRAFICA DE ESTADOS  
  public chartType: ChartType = 'pie';
  public pieChartData = {
    labels: [] as string[],
    datasets: [{
      data: [] as number[],
      label: 'Solicitudes por Estado',
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(201, 203, 207, 0.2)'
      ],
      borderColor: [
        'rgb(255, 99, 132)',
        'rgb(255, 159, 64)',
        'rgb(255, 205, 86)',
        'rgb(75, 192, 192)',
        'rgb(54, 162, 235)',
        'rgb(153, 102, 255)',
        'rgb(201, 203, 207)'
      ],
      borderWidth: 1
    }]
  };
  public pieChartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      legend: {
        position: 'top'
      },
      title: {
        display: true,
        text: 'Solicitudes por Estado',
        color: 'white',
        font: {
          size: 20
        }
      }
    }
  };


  cargarDatos(): void {
    this.solicitudesService.getSolicitudes().subscribe({
      next: (solicitudes: any[]) => {
        const conteoSolicitudes = this.contarSolicitudesPorEstado(solicitudes);

        this.pieChartData.labels = Array.from(conteoSolicitudes.keys());
        this.pieChartData.datasets[0].data = Array.from(conteoSolicitudes.values());

        if (this.chart && this.chart.chart) {
          this.chart.chart.update();
        }
      },
      error: (error) => {
        console.error('Error al cargar las solicitudes:', error);
      }
    });
  }

  private contarSolicitudesPorEstado(solicitudes: any[]): Map<string, number> {
    return solicitudes.reduce((acc, solicitud) => {
      const count = acc.get(solicitud.estado) || 0;
      acc.set(solicitud.estado, count + 1);
      return acc;
    }, new Map<string, number>());
  }

  cambiarGrafico(nuevoTipo: ChartType): void {
    this.chartType = nuevoTipo;
  
    if (this.chart && this.chart.chart) {
      this.chart.chart.destroy();
      this.chart.chart = new Chart(this.chart.chart.canvas, {
        type: nuevoTipo,
        data: this.pieChartData,
        options: this.pieChartOptions as ChartOptions<ChartType>
      });
    }
  }
//GRAFICA DE FECHAS
  
public chartTypeTwo: ChartType = 'bar';
public pieChartDataTwo = {
  labels: [] as string[],
    datasets: [{
      data: [] as number[], 
    label: 'Solicitudes por fecha',
    color: 'white',
    backgroundColor: [
      'rgba(219,38,127,0.5)',
      'rgba(255, 159, 64, 0.5)',
      'rgba(255, 205, 86, 0.5)',
      'rgba(75, 192, 192, 0.5)',
      'rgba(54, 162, 235, 0.5)',
      'rgba(153, 102, 255, 0.5)',
      'rgba(148, 0, 211, 0.5)'
    ],
    borderColor: [
      'rgb(255, 99, 132)',
      'rgb(255, 159, 64)',
      'rgb(255, 205, 86)',
      'rgb(75, 192, 192)',
      'rgb(54, 162, 235)',
      'rgb(153, 102, 255)',
      'rgb(201, 203, 207)'
    ],
    options: {
      scales: {
        x: {
          type: 'linear',
          position: 'bottom'
        },
        y: {
          type: 'linear'
        }
      }
    },
    borderWidth: 1
    
  }]
  
};
public pieChartOptionsTwo: ChartOptions = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top'
    },
    title: {
      display: true,
      text: 'Solicitudes por fecha',
      color: 'white',
      font: {
        size: 20
      }
    }
  }
};
cargarDatosDos(change: string, fInicial: string, fFinial: string): void {
  this.solicitudesService.getSolicitudes().subscribe({
    next: (solicitudes: any[]) => {
        const conteoSolicitudes = this.contarSolicitudesPorFecha(solicitudes, change, fInicial, fFinial);
        this.pieChartDataTwo.labels = Array.from(conteoSolicitudes.keys());
        this.pieChartDataTwo.datasets[0].data = Array.from(conteoSolicitudes.values());

      if (this.chart && this.chart.chart) {
        this.chart.chart.update();
      }
      
    },
    error: (error) => {
      console.error('Error al cargar las solicitudes:', error);
    }
  });
  const boton = document.getElementById('agregar-contenedor');
const contenedorPrincipal = document.getElementById('contenedor-principal');
let contenedor: HTMLElement | null = null;
boton?.addEventListener('click', () => {
  
  if (!contenedor) {
    // Crear un nuevo elemento div
    contenedor = document.createElement('div');
    contenedor.classList.add('oculto'); // Inicialmente oculto

    // Agregar el contenido de los formularios al nuevo contenedor
    contenedor.innerHTML = `
      <label for="">Fecha Inicial</label>
      <input type="date" [(ngModel)]="fechaInicial">
      <label for="">Fecha Final</label>
      <input type="date" [(ngModel)]="fechaFinal">
      <button (click)="cargarDatosDos('estado', fechaInicial, fechaFinal)">Cargar</button>
    `;

    // Agregar el nuevo contenedor al contenedor principal
    contenedorPrincipal?.appendChild(contenedor);
  } else {
    // Toogle la clase oculta para mostrar/ocultar
    contenedor.classList.toggle('oculto');
    console.log('Estado del contenedor:', contenedor.classList.contains('oculto')); // Verificar si la clase se está aplicando correctamente

  }
});
}

private contarSolicitudesPorFecha(solicitudes: any[], change:string, fInicial: string, fFinal: string): Map<string, number> {
  return solicitudes.reduce((acc, solicitud) => {
   if(solicitud.fechaSolicitud >= fInicial && solicitud.fechaSolicitud <= fFinal){
    const count = acc.get(solicitud[change]) || 0;
    acc.set(solicitud[change], count + 1);
   }  
    return acc;
  }, new Map<string, number>());
}

cambiarGraficoDos(nuevoTipo: ChartType): void {
  this.chartTypeTwo = nuevoTipo;

  if (this.chart && this.chart.chart) {
    this.chart.chart.destroy();
    this.chart.chart = new Chart(this.chart.chart.canvas, {
      type: nuevoTipo,
      data: this.pieChartDataTwo,
      options: this.pieChartOptionsTwo as ChartOptions<ChartType>
    });
  }
}

//GRAFICA PARA TALENTOS

public chartTypeThree: ChartType = 'bar';
public pieChartDataThree = {
  labels: [] as string[],
  datasets: [{
    data: [] as number[],
    
    label: 'Solicitudes por Talentos',
    backgroundColor: [
      'rgba(255, 99, 132, 0.2)',
      'rgba(255, 159, 64, 0.2)',
      'rgba(255, 205, 86, 0.2)',
      'rgba(75, 192, 192, 0.2)',
      'rgba(54, 162, 235, 0.2)',
      'rgba(153, 102, 255, 0.2)',
      'rgba(201, 203, 207, 0.2)'
    ],
    borderColor: [
      'rgb(255, 99, 132)',
      'rgb(255, 159, 64)',
      'rgb(255, 205, 86)',
      'rgb(75, 192, 192)',
      'rgb(54, 162, 235)',
      'rgb(153, 102, 255)',
      'rgb(201, 203, 207)'
    ], 
  }]
};
public pieChartOptionsThree: ChartOptions = {
  elements:{
    bar: {
      borderWidth: 2,
    }
  },
  responsive: true,
  plugins: {
    legend: {
      position: 'right',
    },
    title: {
      display: true,
      text: 'Talentos',
      color: 'white',
      font: {
        size: 20
      }
    }
  },
    indexAxis: 'y',
};

cargarDatosTres(): void {
  this.solicitudesService.getSolicitudes().subscribe({
    next: (solicitudes: any[]) => {
      const conteoSolicitudes = this.contarSolicitudesPorTalento(solicitudes);

      this.pieChartDataThree.labels = Array.from(conteoSolicitudes.keys());
      this.pieChartDataThree.datasets[0].data = Array.from(conteoSolicitudes.values());

      if (this.chart && this.chart.chart) {
        this.chart.chart.update();
      }
    },
    error: (error) => {
      console.error('Error al cargar las solicitudes:', error);
    }
  });
}

private contarSolicitudesPorTalento(solicitudes: any[]): Map<string, number> {
  return solicitudes.reduce((acc, solicitud) => {
    const count = acc.get(solicitud) || 0;
    acc.set(solicitud, count + 1);
    return acc;
  }, new Map<string, number>());
}

cambiarGraficoTres(nuevoTipo: ChartType): void {
  this.chartTypeThree = nuevoTipo;

  if (this.chart && this.chart.chart) {
    this.chart.chart.destroy();
    this.chart.chart = new Chart(this.chart.chart.canvas, {
      type: nuevoTipo,
      data: this.pieChartDataThree,
      options: this.pieChartOptionsThree as ChartOptions<ChartType>
    });
  }
}


}