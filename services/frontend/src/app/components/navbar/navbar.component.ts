import { Component } from '@angular/core';
import {MdbCollapseModule} from "mdb-angular-ui-kit/collapse";
import {KeycloakService} from "../../services/keycloak/keycloak.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-navbar',
  standalone: true,
    imports: [
        MdbCollapseModule,
        RouterLink
    ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {

  constructor(private keycloakService: KeycloakService) {
  }

  async logout() {
    await this.keycloakService.logout();
  }

}
