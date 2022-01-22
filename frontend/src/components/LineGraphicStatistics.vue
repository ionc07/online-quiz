<script>
import {Line} from "vue-chartjs";
import api from "@/components/backend-api";
import moment from 'moment';

export default {
  extends: Line,
  data() {
    return {
      weeklyTestCount: []
    }
  },
  mounted() {
    api
        .getWeeklyTestCreations()
        .then(r => {
          this.weeklyTestCount = r.data;
          var dayLabels = this.weeklyTestCount.map(data => {
            return moment(data.date).format('dddd');
          });
          var dayCounts = this.weeklyTestCount.map(data => {
            return data.count;
          });

          this.renderChart(
              {
                labels: dayLabels,
                datasets: [
                  {
                    label: "Count",
                    data: dayCounts,
                    backgroundColor: "transparent",
                    borderColor: "rgba(1, 116, 188, 0.50)",
                    pointBackgroundColor: "rgba(171, 71, 188, 1)"
                  }
                ],
              },
              {
                responsive: true,
                maintainAspectRatio: false,
                title: {
                  display: true,
                  text: "Tests per day"
                }
              }
          );
        });
  }
};
</script>