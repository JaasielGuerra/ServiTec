import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ConsultarclientesComponent } from "./clientes/consultarclientes/consultarclientes.component";
import { CrearclienteComponent } from "./clientes/crearcliente/crearcliente.component";
import { AdminLayoutRoutes } from "../layouts/admin-layout/admin-layout.routing";
import { RouterModule } from "@angular/router";
import { ConsultarordenesservicioComponent } from "./ordenesservicio/consultarordenesservicio/consultarordenesservicio.component";
import { CrearordenservicioComponent } from "./ordenesservicio/crearordenservicio/crearordenservicio.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { CatalogomotivosComponent } from "./ordenesservicio/catalogomotivos/catalogomotivos.component";
import { FormsModule } from "@angular/forms";
import { ConsultarordenesatenderComponent } from "./atenderordenes/consultarordenesatender/consultarordenesatender.component";
import { AtenderordenComponent } from "./atenderordenes/atenderorden/atenderorden.component";
import { ConsultarordenescobrarComponent } from "./cobroservicios/consultarordenescobrar/consultarordenescobrar.component";
import { ConsultarservicioscobradosComponent } from "./cobroservicios/consultarservicioscobrados/consultarservicioscobrados.component";
import { CobrarservicioComponent } from "./cobroservicios/cobrarservicio/cobrarservicio.component";
import { HttpClientModule } from "@angular/common/http";
import { OrdenesService } from "./services/ordenes.service";
import { TecnicosService } from "../areatecnica/service/tecnicos.service";
import { PrioridadesService } from "./services/prioridades.service";
import { ClientesService } from "./services/clientes.service";
import { MotivosordenService } from "./services/motivosorden.service";
import { ToastrModule } from "ngx-toastr";
import { EditarordenservicioComponent } from "./ordenesservicio/editarordenservicio/editarordenservicio.component";
import { EstadoordenService } from "./services/estadoorden.service";
import { InventarioService } from "../areatecnica/service/inventario.service";
import { ServiciostecnicosService } from "../areatecnica/service/serviciostecnicos.service";
import { RepuestosService } from "./services/repuestos.service";
import { ServiciosService } from "./services/servicios.service";
import { FotosService } from "./services/fotos.service";

@NgModule({
  declarations: [
    ConsultarclientesComponent,
    CrearclienteComponent,
    ConsultarordenesservicioComponent,
    CrearordenservicioComponent,
    CatalogomotivosComponent,
    ConsultarordenesatenderComponent,
    AtenderordenComponent,
    ConsultarordenescobrarComponent,
    ConsultarservicioscobradosComponent,
    CobrarservicioComponent,
    EditarordenservicioComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    NgbModule,
    FormsModule,
    HttpClientModule,
    ToastrModule,
  ],
  providers: [
    OrdenesService,
    TecnicosService,
    PrioridadesService,
    ClientesService,
    MotivosordenService,
    EstadoordenService,
    InventarioService,
    ServiciostecnicosService,
    RepuestosService,
    ServiciosService,
    FotosService
  ],
})
export class TallerModule {}
