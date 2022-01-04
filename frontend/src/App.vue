<template>
  <v-app class="grey lighten-4" v-scroll="onScroll">
    <div v-if="$store.state.app.auth.loggedIn">
      <NavBar/>

      <v-main name="content">
        <ToolBar/>

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
    <div v-if="!$store.state.app.auth.loggedIn">
      <Authentication/>
    </div>
  </v-app>
</template>

<script>
import NavBar from "./components/NavBar";
import ToolBar from "./components/ToolBar";
import "./assets/styles/app.css";
import Authentication from "@/views/Authentication";

export default {
  components: {Authentication, NavBar, ToolBar},
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
      if (val.target.scrollingElement.scrollTop > 300)
        this.showScrollBtn = true;
      else this.showScrollBtn = false;
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