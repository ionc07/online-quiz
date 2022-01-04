<template>
  <nav>
    <v-navigation-drawer
        app
        v-model="drawer"
        permanent
        :mini-variant="$vuetify.breakpoint.smAndDown"
        width="224"
    >
      <v-list-item two-line class="logo">
        <v-img
            max-width="18%"
            src="@/assets/quiz-logo.png"
        ></v-img>
        <v-list-item-content>
          <v-list-item-title class="pl-3">QuizTerra</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list nav class="pl-0 pr-0">
        <v-list-item
            v-for="item in getNavLinks()"
            :key="item.title"
            link
            :class="
            $store.state.app.activeRoute === item.title
              ? `active-link ${item.classes}`
              : item.classes
          "
            @click="navLinkClick(item.title, item.route)"
            class="pl-0 pr-0 pt-0 pb-0"
            v-ripple="{ class: `white--text` }"
        >
          <div :class="$store.state.app.activeRoute === item.title
              ? `active-link active-link-bar ${item.classes}`
              : `active-link-bar`"></div>
          <v-list-item-icon
              :class="
              $store.state.app.activeRoute === item.title ? 'active-link' : ''
            "
          >
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title
                :class="
                $store.state.app.activeRoute === item.title ? 'active-link' : ''
              "
            >{{ item.title }}
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      drawer: true,
      items: [
        {icon: "mdi-plus-circle", title: "Create test", route: "/create"},
        {icon: "mdi-school", title: "My tests", route: "/tests"},
        {icon: "mdi-account-supervisor-circle", title: "User groups", route: "/userGroups"},
        {icon: "mdi-account", title: "Account", route: "/account"},
      ],
      adminItems: [
        {
          icon: "mdi-view-dashboard",
          title: "Dashboard",
          route: "/dashboard",
        },
        {icon: "mdi-account-multiple", title: "Manage users", route: "/users"},
        {icon: "mdi-account", title: "Account", route: "/account"},
      ],
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
    },
  },
  beforeMount() {
    let currentPath = this.$router.history.current.path;
    this.$store.state.app.activeRoute = "My tests";
    this.items.map((link) => {
      if (currentPath.includes(link.title.toLowerCase()))
        this.$store.state.app.activeRoute = link.title;
    });
  },
};
</script>

<style lang="css" scoped>
nav {
  /*background-color: #001f3f !important;*/
}

.logo {
  padding: 5px 5px 5px 8px !important;
}

.v-navigation-drawer__content .v-list .v-list-item .v-list-item__icon i {
  margin-left: 16px;
  color: #035071 !important;
}

.v-navigation-drawer__content
.v-list
.v-list-item
.v-list-item__icon.active-link
i {
  transition: 0.3s;
  color: #0c98c9 !important;
}

.v-navigation-drawer__content
.v-list
.v-list-item
.v-list-item__content
.v-list-item__title,
.v-navigation-drawer__content
.v-list-item
.v-list-item__content
.v-list-item__title {
  margin-left: 15px;
  /* color: #afb9c2 !important; */
}

.v-navigation-drawer__content
.v-list
.v-list-item
.v-list-item__content
.v-list-item__title.active-link {
  transition: 0.3s;
  color: #0c98c9 !important;
}

.theme--light.v-divider {
  border-top: 1px solid #9b9b9b !important;
}

.logout-link {
  width: 100% !important;
  position: absolute !important;
  bottom: 16px !important;
}

.active-link-bar.active-link {
  height: 55px;
  background-color: #0c98c9;
  transition: 0.2s;
}

.active-link-bar {
  display: block;
  position: absolute;
  width: 5px;
  height: 0;
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
  background-color: transparent;
}
</style>
