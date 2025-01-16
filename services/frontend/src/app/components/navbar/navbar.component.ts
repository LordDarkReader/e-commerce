import {Component, OnInit} from '@angular/core';
import {MdbCollapseModule} from "mdb-angular-ui-kit/collapse";
import {KeycloakService} from "../../services/keycloak/keycloak.service";
import {RouterLink} from "@angular/router";
import {NgIf} from "@angular/common";
import {TestService} from "../../services/test.service";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MdbCollapseModule,
    RouterLink,
    NgIf
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit {

  constructor(private keycloakService: KeycloakService, private testService: TestService) {
  }

  isLogged : boolean;
  loginName : string;

  async logout() {
    await this.keycloakService.logout();
  }

  async login() {
    await this.keycloakService.login();
  }

  async register() {
    await this.keycloakService.register();
  }

  ngOnInit(): void {
    if (this.keycloakService.profile) {
      console.log("logged");
      this.isLogged = true;
      this.loginName = this.keycloakService.profile.username
    } else {
      console.log("not logged");
      this.isLogged = false;
    }
  }
}
