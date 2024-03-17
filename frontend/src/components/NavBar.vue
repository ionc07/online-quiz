<template>
  <nav>
    <v-navigation-drawer
        name="navigation"
        app
        v-model="drawer"
        permanent
        :mini-variant="$vuetify.breakpoint.smAndDown || $store.state.nav.miniNavBar"
        width="224"
    >
      <v-list-item two-line class="logo">
        <v-img max-width="40px" src="@/assets/quiz-logo.png"></v-img>

      </v-list-item>

      <v-divider></v-divider>

      <v-list nav class="pl-0 pr-0">
        <v-list-item
            v-for="item in getNavLinks()"
            :key="item.title"
            link
            :to="item.route"
            v-ripple="{ class: `white--text` }"
        >
          <v-list-item-icon>
            <v-icon size="30px">{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-title>
            {{ item.title }}
          </v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>

export default {
  components: {},
  data() {
    return {
      drawer: true,
      items: [
        {icon: "mdi-plus-circle", title: "Create test", route: "/createTest"},
        {icon: "mdi-school", title: "My tests", route: "/tests"},
        {
          icon: "mdi-account-supervisor-circle",
          title: "User groups",
          route: "/userGroups"
        },
        {icon: "mdi-account", title: "Account", route: "/account"}
      ],
      adminItems: [
        {
          icon: "mdi-view-dashboard",
          title: "Dashboard",
          route: "/dashboard"
        },
        {
          icon: "mdi-account-multiple",
          title: "Manage users",
          route: "/users"
        },
        {icon: "mdi-account", title: "Account", route: "/account"}
      ]
    };
  },
  methods: {
    getNavLinks() {
      if (this.$store.state.admin) {
        return this.adminItems;
      } else {
        return this.items;
      }
    },
    navLinkClick(title, route) {
      if (title !== "Logout") {
        this.$store.state.app.activeRoute = title;
        this.$router.history.push(route);
      }
    }
  }
};
</script>

<style lang="css" scoped>
nav {
  /*background-color: #001f3f !important;*/
}

.logo {
  padding: 5px 5px 5px 8px !important;
}

.v-list-item--active {
  background-color: #035071;
  color: #ffffff;
  transition: 0.3s;
}

.v-list-item--active div {
  color: #ffffff;
}

.v-list-item--active div.v-list-item__icon i {
  color: #ffffff !important;
}

.v-list-item .v-list-item__icon i {
  margin-left: 5px;
  color: #035071 !important;
}

.line {
  display: none;
  height: 50px;
  width: 3px;
  background-color: #690101 !important;
}

.theme--light.v-divider {
  border-top: 1px solid #9b9b9b !important;
}

.logout-link {
  width: 100% !important;
  position: absolute !important;
  bottom: 16px !important;
}

@media only screen and (max-width: 960px) {
}
</style>
