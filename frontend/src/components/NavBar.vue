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
      <v-list-item two-line class="logo ml-5">
        <v-img max-width="40px" src="@/assets/quiz-logo.png"></v-img>

      </v-list-item>

      <v-divider></v-divider>

      <v-list nav class="pl-0 pr-0">
        <div v-for="link in getNavLinks()">
          <v-list-item
              v-if="!link.subLinks"
              :key="link.text"
              link
              :to="link.to"
              v-ripple="{ class: `white--text` }"
          >
            <v-list-item-icon>
              <v-icon size="30px">{{ link.icon }}</v-icon>
            </v-list-item-icon>

            <v-list-item-title>
              {{ link.text }}
            </v-list-item-title>
          </v-list-item>

          <v-list-group
              v-else
              no-action
              :prepend-icon="link.icon"
              :value="false"
          >
            <template v-slot:activator>
              <v-list-item-title class="list-group-text">{{ link.text }}</v-list-item-title>
            </template>

            <v-list-item
                v-for="sublink in link.subLinks"
                :to="sublink.to"
                :key="sublink.text"
            >
              <v-list-item-icon>
                <v-icon>{{ sublink.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>{{ sublink.text }}</v-list-item-title>

            </v-list-item>

          </v-list-group>
        </div>
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
      links: [
        {icon: "mdi-plus-circle", text: "Create test", to: "/createTest"},
        {
          icon: "mdi-school", text: "Tests",
          subLinks: [
            {
              text: 'My Tests',
              to: '/myTests',
            },
            {
              text: 'Shared tests',
              to: '/sharedTests',
            },
            {
              text: 'Test results',
              to: '/testResults',
            },
          ]
        },
        {
          icon: "mdi-account-supervisor-circle",
          text: "User groups",
          to: "/userGroups"
        },
        {
          icon: "mdi-clipboard-text-multiple-outline",
          text: "Test groups",
          to: "/testGroups"
        },

      ],
      adminItems: [
        {
          icon: "mdi-view-dashboard",
          text: "Dashboard",
          to: "/dashboard"
        },
        {
          icon: "mdi-account-multiple",
          text: "Manage users",
          to: "/users"
        },
        {icon: "mdi-account", text: "Account", to: "/account"}
      ]
    };
  },
  methods: {
    getNavLinks() {
      if (this.$store.state.admin) {
        return this.adminItems;
      } else {
        return this.links;
      }
    },
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

.list-group-text {
  color: #212121 !important;
}

.v-list-group__items a {
  padding-left: 30px !important;
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
