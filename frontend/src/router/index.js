import Vue from "vue";
import VueRouter from "vue-router";
//import store from '../store'

Vue.use(VueRouter);

const routes = [
  {
    path: "/home",
    name: "Home",
    component: () =>
        import("../views/Home.vue"),
  },
  {
    path: "/userGroups",
    name: "Users",
    component: () =>
        import("../views/Users.vue"),
  },
  {
    path: "/login",
    name: "Authentication",
    component: () =>
        import("../views/Authentication.vue"),
  },
  {
    path: "/users",
    name: "Users",
    component: () =>
        import("../views/Users.vue"),
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: () =>
        import("../views/Dashboard.vue"),
  },
];

const router = new VueRouter({
  routes,
  mode: "history",
});


router.beforeEach((to, from, next) => {
  // if (to.matched.some(record => record.meta.requiresAuth)) {
  // this route requires auth, check if logged in
  // if not, redirect to login page.
  if (to.path !== "/login")
      // if (localStorage.getItem("access_token") == null) {
      //   store.state.app.auth.loggedIn = false;
      //   next({
      //     path: "/login"
      //   });
      // } else {
      //   next();
      // }
    next(); // make sure to always call next()!
});
export default router;
