import { Routes } from "@angular/router";

import { DashboardComponent } from "../../dashboard/dashboard.component";

import { ConsultarclientesComponent } from "../../taller/clientes/consultarclientes/consultarclientes.component";
import { ReportesComponent } from "../../administracion/reportes/reportes.component";
import { ConsultarserviciostecnicosComponent } from "../../areatecnica/serviciostecnicos/consultarserviciostecnicos/consultarserviciostecnicos.component";
import { ConsultarcatalogocategoriasComponent } from "../../areatecnica/serviciostecnicos/catalogocategorias/consultarcatalogo/consultarcatalogocategorias.component";
import { CrearserviciotecnicoComponent } from "../../areatecnica/serviciostecnicos/crearserviciotecnico/crearserviciotecnico.component";
import { ConsultarpersonalComponent } from "../../areatecnica/personaltecnico/consultarpersonal/consultarpersonal.component";
import { ConsultainventarioComponent } from "../../areatecnica/inventariorepuestos/consultainventario/consultainventario.component";
import { EditarserviciotecnicoComponent } from "../../areatecnica/serviciostecnicos/editarserviciotecnico/editarserviciotecnico.component";
import { Status404Component } from "../status404/status404.component";
import { CrearclienteComponent } from "../../taller/clientes/crearcliente/crearcliente.component";
import { RegistrarpersonalComponent } from "../../areatecnica/personaltecnico/registrarpersonal/registrarpersonal.component";
import { NuevorepuestoComponent } from "../../areatecnica/inventariorepuestos/nuevorepuesto/nuevorepuesto.component";
import { CatalogosinventarioComponent } from "../../areatecnica/inventariorepuestos/catalogosinventario/catalogosinventario.component";
import { AgregarinventarioComponent } from "../../areatecnica/inventariorepuestos/agregarinventario/agregarinventario.component";
import { AjustarinventarioComponent } from "../../areatecnica/inventariorepuestos/ajustarinventario/ajustarinventario.component";
import { ConsultarordenesservicioComponent } from "../../taller/ordenesservicio/consultarordenesservicio/consultarordenesservicio.component";
import { CrearordenservicioComponent } from "../../taller/ordenesservicio/crearordenservicio/crearordenservicio.component";
import { CatalogomotivosComponent } from "../../taller/ordenesservicio/catalogomotivos/catalogomotivos.component";
import { ConsultarusuariosComponent } from "../../administracion/usuarios/consultarusuarios/consultarusuarios.component";
import { ConsultarrolesComponent } from "../../administracion/usuarios/roles/consultarroles/consultarroles.component";
import { CrearusuarioComponent } from "../../administracion/usuarios/crearusuario/crearusuario.component";
import { ConsultarordenesatenderComponent } from "../../taller/atenderordenes/consultarordenesatender/consultarordenesatender.component";
import { AtenderordenComponent } from "../../taller/atenderordenes/atenderorden/atenderorden.component";
import { ConsultarordenescobrarComponent } from "../../taller/cobroservicios/consultarordenescobrar/consultarordenescobrar.component";
import { ConsultarservicioscobradosComponent } from "../../taller/cobroservicios/consultarservicioscobrados/consultarservicioscobrados.component";
import { CobrarservicioComponent } from "../../taller/cobroservicios/cobrarservicio/cobrarservicio.component";

/**
 * Configuracion de rutas para navegacion de la aplicacion del menu principal
 */
export const AdminLayoutRoutes: Routes = [
  { path: "notfound", component: Status404Component },
  { path: "dashboard", component: DashboardComponent },
  { path: "ordenes-servicio", component: ConsultarordenesservicioComponent },
  { path: "ordenes-servicio/crear", component: CrearordenservicioComponent },
  { path: "ordenes-servicio/catalogo-motivos", component: CatalogomotivosComponent },
  { path: "atender-ordenes", component: ConsultarordenesatenderComponent },
  { path: "atender-ordenes/atender/:id", component: AtenderordenComponent },
  { path: "cobro-servicios", component: ConsultarordenescobrarComponent },
  { path: "cobro-servicios/cobros", component: ConsultarservicioscobradosComponent },
  { path: "cobro-servicios/cobrar/:id", component: CobrarservicioComponent },
  { path: "clientes", component: ConsultarclientesComponent },
  { path: "clientes/crear", component: CrearclienteComponent },
  { path: "servicios-tecnicos", component: ConsultarserviciostecnicosComponent },
  { path: 'servicios-tecnicos/categorias', component: ConsultarcatalogocategoriasComponent },
  { path: 'servicios-tecnicos/nuevo', component: CrearserviciotecnicoComponent },
  { path: 'servicios-tecnicos/editar/:id', component: EditarserviciotecnicoComponent },
  { path: "personal-tecnico", component: ConsultarpersonalComponent },
  { path: "personal-tecnico/crear", component: RegistrarpersonalComponent },
  { path: "inventario-repuestos", component: ConsultainventarioComponent },
  { path: "inventario-repuestos/nuevo", component: NuevorepuestoComponent },
  { path: "inventario-repuestos/agregar/:id", component: AgregarinventarioComponent },
  { path: "inventario-repuestos/ajustar/:id", component: AjustarinventarioComponent },
  { path: "inventario-repuestos/catalogos", component: CatalogosinventarioComponent },
  { path: "usuarios", component: ConsultarusuariosComponent },
  { path: "usuarios/roles", component: ConsultarrolesComponent },
  { path: "usuarios/nuevo", component: CrearusuarioComponent },
  { path: "reportes", component: ReportesComponent },
];
