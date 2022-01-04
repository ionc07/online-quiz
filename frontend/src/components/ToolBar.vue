<template>
  <v-toolbar dense outlined>
    <v-toolbar-title>{{ $store.state.app.activeRoute }}</v-toolbar-title>
    <!--    <div class="admin-switch">-->
    <!--      <v-switch v-model="admin" label="Admin" @change="changeAdminState"></v-switch>-->
    <!--    </div>-->
    <v-progress-linear
        v-show="$store.state.app.fetchLoading"
        color="blue"
        rounded
        slot="extension"
        :indeterminate="true"
    />
  </v-toolbar>
</template>
<script>
export default {
  name: "ToolBar",
  data: () => ({
    admin: false
  }),
  methods: {
    changeAdminState() {
      this.admin = !this.admin;
      this.$store.state.admin = this.admin;
    }
  }
}
</script>
<style>
.v-toolbar {
  margin-left: 8px;
  padding-top: 15px;
  height: 64px !important;
  display: block !important;
}

.v-toolbar__content,
.v-toolbar__extension {
  height: 32px !important;
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

</style>