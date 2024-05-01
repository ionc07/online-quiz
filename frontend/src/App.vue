<template>
  <v-app class="grey lighten-4" v-scroll="onScroll">
    <div>
      <NavBar v-if="isLoggedIn" />
      <ToolBar v-if="isLoggedIn" />

      <v-main name="content">
        <div class="app-container">
          <div class="content">
            <!-- Conditionally render the router view based on user authentication -->
            <router-view v-if="isLoggedIn"></router-view>
            <!-- Render Login or Register component if user is not logged in -->
            <router-view v-else></router-view>
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
import { mapState } from "vuex";
import "./assets/styles/app.css";

export default {
  components: { NavBar, ToolBar },
  name: "App",
  computed: {
    ...mapState({
      isLoggedIn: state => state.auth.status.loggedIn
    })
  },
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
  background-color: rgba(152, 218, 245, 0.73) !important;
}
</style>
