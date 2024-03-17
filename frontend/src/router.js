import Vue from "vue";
import VueRouter from "vue-router";
import UsersView from "./views/Users.vue";
import DashBoardView from "./views/Dashboard.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";
import store from './store'
import Home from "@/views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/createTest",
    name: "Create test",
    meta: {requiresAuth: true},
    component: () => import("./views/CreateTest.vue")
  },
  {
    path: "/tests",
    name: "UserTests",
    meta: {requiresAuth: true},
    component: () => import("./views/UserTests.vue")
  },
  {
    path: "/userGroups",
    name: "User groups",
    meta: {requiresAuth: true},
    component: () => import("./views/UserGroups.vue")
  },
  {
    path: "/users",
    name: "Users",
    component: UsersView,
    meta: {
      requiresAuth: true,
      role: "ROLE_admin"
    }
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: DashBoardView,
    meta: {requiresAuth: true, role: "ROLE_admin"}
  },
  {
    path: "/login", component: Login,
    name: "Login",
    meta: {
      requiresGuest: true,
    }
  },
  {
    path: "/register", component: Register,
    name: "Register",
    meta: {
      requiresGuest: true,
    }
  },
  {
    path: "/account", component: Home,
    name: "Account",
    meta: {
      requiresAuth: true,
    }
  },
  // otherwise redirect to tests
  {path: "*", redirect: "/tests"}
];

const router = new VueRouter({
  routes,
  mode: "history"
});

router.beforeEach((to, from, next) => {
  // if (to.matched.some(record => record.meta.requiresAuth)) {
  // this route requires auth, check if logged in
  // if not, redirect to login page.

  // redirect to login page if not logged in and trying to access a restricted page
  // const publicPages = ["/login"];
  // const authRequired = !publicPages.includes(to.path);
  // const loggedIn = localStorage.getItem("user");

  const requiresAuth = to.matched.some((x) => x.meta.requiresAuth);
  const requiresGuest = to.matched.some((x) => x.meta.requiresGuest);
  const isLoggedIn = store.state.auth.status.loggedIn;
  console.log(router);
  console.log(store.state);
  console.log("requiresAuth: " + requiresAuth);
  console.log("requiresGuest: " + requiresGuest);
  console.log("isLoggedIn: " + isLoggedIn)
  if (requiresAuth && !isLoggedIn) {
    return next("/login");
  } else if (requiresGuest && to.name === "Login") {
    return next("/tests");
  }

  next();
});
export default router;
