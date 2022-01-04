<template>
  <v-app>
    <Login @switchActionType="switchActionType" v-if="actionType === 'Login'" v-bind:action-type="actionType"/>
    <SignUp @switchActionType="switchActionType" v-if="actionType === 'SignUp'"/>
  </v-app>
</template>

<script>
import Login from "@/components/Login";
import SignUp from "@/components/SignUp";

export default {
  name: "Authentication",
  components: {SignUp, Login},
  data() {
    return {
      actionType: "Login"
    }
  },
  methods: {
    switchActionType(actionType) {
      this.actionType = actionType;
    }
  }
}
</script>

<style scoped>

</style>