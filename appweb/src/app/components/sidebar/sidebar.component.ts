import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'design_app', class: '' },

    // taller
    { path: '/ordenes-servicio', title: 'Ordenes servicio',  icon:'emoticons_satisfied', class: '' },
    { path: '/atender-ordenes', title: 'Atender ordenes',  icon:'emoticons_satisfied', class: '' },
    { path: '/cobro-servicios', title: 'Cobro servicios',  icon:'emoticons_satisfied', class: '' },
    { path: '/clientes', title: 'Clientes',  icon:'emoticons_satisfied', class: '' },

    //area tecnica
    { path: '/servicios-tecnicos', title: 'Servicios tecnicos',  icon:'emoticons_satisfied', class: '' },
    { path: '/personal-tecnico', title: 'Personal tecnico',  icon:'emoticons_satisfied', class: '' },
    { path: '/inventario-repuestos', title: 'Inventario repuestos',  icon:'emoticons_satisfied', class: '' },

    //administracion
    { path: '/usuarios', title: 'Usuarios',  icon:'emoticons_satisfied', class: '' },
    { path: '/reportes', title: 'Reportes',  icon:'emoticons_satisfied', class: '' }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  };
}
