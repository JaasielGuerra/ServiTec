import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AdminLayoutRoutes } from "../layouts/admin-layout/admin-layout.routing";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ConsultarserviciostecnicosComponent } from "./serviciostecnicos/consultarserviciostecnicos/consultarserviciostecnicos.component";
import { CrearserviciotecnicoComponent } from "./serviciostecnicos/crearserviciotecnico/crearserviciotecnico.component";
import { ConsultarcatalogocategoriasComponent } from "./serviciostecnicos/catalogocategorias/consultarcatalogo/consultarcatalogocategorias.component";
import { ConsultarpersonalComponent } from "./personaltecnico/consultarpersonal/consultarpersonal.component";
import { RegistrarpersonalComponent } from "./personaltecnico/registrarpersonal/registrarpersonal.component";
import { EditarpersonalComponent } from "./personaltecnico/editarpersonal/editarpersonal.component";
import { NuevorepuestoComponent } from "./inventariorepuestos/nuevorepuesto/nuevorepuesto.component";
import { ConsultarcajasComponent } from "./inventariorepuestos/cajas/consultarcajas/consultarcajas.component";
import { ConsultarestantesComponent } from "./inventariorepuestos/estantes/consultarestantes/consultarestantes.component";
import { ConsultarubicacionesComponent } from "./inventariorepuestos/ubicaciones/consultarubicaciones/consultarubicaciones.component";
import { ConsultainventarioComponent } from "./inventariorepuestos/consultainventario/consultainventario.component";
import { ServiciostecnicosService } from "./service/serviciostecnicos.service";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { CategoriaservicioService } from "./service/categoriaservicio.service";
import { EditarserviciotecnicoComponent } from "./serviciostecnicos/editarserviciotecnico/editarserviciotecnico.component";
import { CatalogosinventarioComponent } from "./inventariorepuestos/catalogosinventario/catalogosinventario.component";
import { AgregarinventarioComponent } from "./inventariorepuestos/agregarinventario/agregarinventario.component";
import { AjustarinventarioComponent } from "./inventariorepuestos/ajustarinventario/ajustarinventario.component";
import { InventarioService } from "./service/inventario.service";
import { EstanteService } from "./service/estante.service";
import { CajaService } from "./service/caja.service";
import { UbicacionService } from "./service/ubicacion.service";
import { EditarrepuestoComponent } from "./inventariorepuestos/editarrepuesto/editarrepuesto.component";
import { TecnicosService } from "./service/tecnicos.service";
import { UsuariosService } from "../administracion/service/usuarios.service";

@NgModule({
  declarations: [
    ConsultarserviciostecnicosComponent,
    CrearserviciotecnicoComponent,
    ConsultarcatalogocategoriasComponent,
    ConsultarpersonalComponent,
    RegistrarpersonalComponent,
    EditarpersonalComponent,
    NuevorepuestoComponent,
    ConsultarcajasComponent,
    ConsultarestantesComponent,
    ConsultarubicacionesComponent,
    ConsultainventarioComponent,
    EditarserviciotecnicoComponent,
    CatalogosinventarioComponent,
    AgregarinventarioComponent,
    AjustarinventarioComponent,
    EditarrepuestoComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    NgbModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    ServiciostecnicosService,
    CategoriaservicioService,
    InventarioService,
    EstanteService,
    CajaService,
    UbicacionService,
    TecnicosService,
    UsuariosService
  ],
})
export class AreatecnicaModule {}
