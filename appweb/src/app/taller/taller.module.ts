import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrdenesservicioComponent } from './ordenesservicio/ordenesservicio.component';
import { AtenderordenesComponent } from './atenderordenes/atenderordenes.component';
import { CobroserviciosComponent } from './cobroservicios/cobroservicios.component';
import { ClientesComponent } from './clientes/clientes.component';



@NgModule({
  declarations: [OrdenesservicioComponent, AtenderordenesComponent, CobroserviciosComponent, ClientesComponent],
  imports: [
    CommonModule
  ]
})
export class TallerModule { }
