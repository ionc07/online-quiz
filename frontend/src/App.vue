<template>
  <v-app class="grey lighten-4" v-scroll="onScroll">
    <div v-if="this.$store.state.auth.status.loggedIn">
      <NavBar />

      <v-main name="content">
        <ToolBar />
        <div class="app-container">
          <div class="content">
            <router-view></router-view>
          </div>
        </div>
        <v-btn
          class="scroll-btn"
          v-scroll="onScroll"
          v-show="showScrollBtn"
          fab
          large
          dark
          fixed
          bottom
          right
          @click="toTop"
        >
          <v-icon>mdi-chevron-up</v-icon>
        </v-btn>
      </v-main>
    </div>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar";
import ToolBar from "./components/ToolBar";
import "./assets/styles/app.css";

export default {
  components: {NavBar, ToolBar },
  name: "App",
  data() {
    return {
      showScrollBtn: false
    };
  },
  methods: {
    toTop() {
      window.scrollTo({
        top: 0,
        behavior: "smooth"
      });
    },
    onScroll(val) {
      this.showScrollBtn = val.target.scrollingElement.scrollTop > 300;
    }
  }
};
</script>
<style scoped>
.scroll-btn {
  background-color: #001f3f !important;
}

/*.app-container {*/
/*  height: 100%;*/
/*  width: 100%;*/
/*  padding: 3px;*/
/*  background: green;*/
/*  box-shadow: 0 3px 10px #0a0a0a;*/
/*}*/

/*.content {*/
/*  height: 100%;*/
/*  overflow: auto;*/
/*  background: red;*/
/*}*/
</style>
