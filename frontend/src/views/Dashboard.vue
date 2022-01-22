<template>
  <div class="statistics-content">
    <QuantityStatistics/>
    <v-card
        elevation="4"
        class="pb-6"
    >
      <div class="line-chart">
        <v-card-title>Weekly statistics</v-card-title>
        <LineGraphicStatistics/>
      </div>
    </v-card>
  </div>
</template>

<script>
import QuantityStatistics from "@/components/QuantityStatistics";
import LineGraphicStatistics from "@/components/LineGraphicStatistics";

export default {
  name: "Dashboard",
  components: {LineGraphicStatistics, QuantityStatistics},
  data() {
    return {}
  },

  methods: {}
}
</script>

<style scoped>
.statistics-content {
  padding-top: 20px;
  padding-left: 10px;
  padding-right: 10px;
}

.line-chart {
  width: 100%;
  margin-top: 10px;
}
@media only screen and (min-width: 960px) {
  .line-chart {
    width: 50%;
  }
}

</style>