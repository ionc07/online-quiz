<template>
  <v-row justify="center">
    <v-dialog v-model="display" persistent max-width="290">
      <v-card class="card-with-border">
        <v-card-title class="headline red">
          {{ title }}
        </v-card-title>
        <v-card-text>
          <div class="mt-2">{{ message }}</div></v-card-text
        >
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn outlined text :ripple="false" @click="$emit('close')">
            Cancel
          </v-btn>
          <v-btn class="text--white" color="red" @click="$emit('confirm')">
            Yes
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
<script>
export default {
  props: ["title", "message", "display"],
  data() {
    return {};
  }
};
</script>
<style lang="css" scoped>
.v-card__title {
  color: white !important;
}
.text--white {
  color: white !important;
}
</style>