<template>
  <v-toolbar dense outlined>
    <v-app-bar-nav-icon @click="$store.state.nav.miniNavBar = !$store.state.nav.miniNavBar"></v-app-bar-nav-icon>
    <v-toolbar-title>{{ $route.name }}</v-toolbar-title>
    <v-progress-linear
        v-show="false"
        color="blue"
        rounded
        slot="extension"
        :indeterminate="true"
    />
    <v-spacer></v-spacer>
    <v-tooltip bottom>
      <template v-slot:activator="{ on, attrs }">
        <v-btn icon @click="logOut"
               v-bind="attrs"
               v-on="on">
          <v-icon>mdi-export</v-icon>
        </v-btn>
      </template>
      <span>Logout</span>
    </v-tooltip>

  </v-toolbar>
</template>
<script>
export default {
  name: "ToolBar",
  data: () => ({
    admin: false
  }),
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
}
</script>
<style>
.v-toolbar {
  padding-top: 14px;
  height: 74px !important;
  display: block !important;
}

.v-toolbar__extension {
  padding: 0px !important;
}

.v-progress-linear__background {
  background-color: red !important;
}

.admin-switch {
  margin-left: auto;
  margin-right: 450px;
}

.v-input--switch {
  padding-top: 18px !important;
}

.v-icon {
  color: #035071 !important;
}

</style>