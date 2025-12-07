package ru.draen.parallel.process;

import lombok.AllArgsConstructor;
import org.apache.spark.api.java.JavaRDD;
import ru.draen.parallel.model.AppConfig;
import ru.draen.parallel.model.AppContext;
import ru.draen.parallel.model.data.CalcData;
import ru.draen.parallel.model.data.SaleData;
import ru.draen.parallel.spark.DatasetUtils;

@AllArgsConstructor
public class SalesProcessor {
    private final AppContext ctx;
    private final AppConfig cfg;

    public void process() {
        JavaRDD<SaleData> sales = DatasetUtils.readData(ctx, cfg.getInputPath(), SaleData.SCHEMA);
        JavaRDD<CalcData> result = sales
                .keyBy(SaleData::getCategory)
                .aggregateByKey(
                        CalcData.empty(),
                        (acc, item) -> {
                            acc.setQuantity(acc.getQuantity() + item.getQuantity());
                            acc.setRevenue(acc.getRevenue() + item.getPrice());
                            return acc;
                        },
                        (acc1, acc2) -> {
                            acc1.setQuantity(acc1.getQuantity() + acc2.getQuantity());
                            acc1.setRevenue(acc1.getRevenue() + acc2.getRevenue());
                            return acc1;
                        })
                .map(tuple -> {
                    tuple._2.setCategory(tuple._1);
                    return tuple._2;
                });
        DatasetUtils.writeData(ctx, cfg.getOutputPath(), CalcData.SCHEMA, result);
    }
}
