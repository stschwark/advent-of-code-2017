import io.kotlintest.matchers.shouldEqual
import io.kotlintest.specs.StringSpec

private const val INPUT = "278\t1689\t250\t1512\t1792\t1974\t175\t1639\t235\t1635\t1690\t1947\t810\t224\t928\t859\n" +
        "160\t50\t55\t81\t68\t130\t145\t21\t211\t136\t119\t78\t174\t155\t149\t72\n" +
        "4284\t185\t4499\t273\t4750\t4620\t4779\t4669\t2333\t231\t416\t1603\t197\t922\t5149\t2993\n" +
        "120\t124\t104\t1015\t1467\t110\t299\t320\t1516\t137\t1473\t132\t1229\t1329\t1430\t392\n" +
        "257\t234\t3409\t2914\t2993\t3291\t368\t284\t259\t3445\t245\t1400\t3276\t339\t2207\t233\n" +
        "1259\t78\t811\t99\t2295\t1628\t3264\t2616\t116\t3069\t2622\t1696\t1457\t1532\t268\t82\n" +
        "868\t619\t139\t522\t168\t872\t176\t160\t1010\t200\t974\t1008\t1139\t552\t510\t1083\n" +
        "1982\t224\t3003\t234\t212\t1293\t1453\t3359\t326\t3627\t3276\t3347\t1438\t2910\t248\t2512\n" +
        "4964\t527\t5108\t4742\t4282\t4561\t4070\t3540\t196\t228\t3639\t4848\t152\t1174\t5005\t202\n" +
        "1381\t1480\t116\t435\t980\t1022\t155\t1452\t1372\t121\t128\t869\t1043\t826\t1398\t137\n" +
        "2067\t2153\t622\t1479\t2405\t1134\t2160\t1057\t819\t99\t106\t1628\t1538\t108\t112\t1732\n" +
        "4535\t2729\t4960\t241\t4372\t3960\t248\t267\t230\t5083\t827\t1843\t3488\t4762\t2294\t3932\n" +
        "3245\t190\t2249\t2812\t2620\t2743\t2209\t465\t139\t2757\t203\t2832\t2454\t177\t2799\t2278\n" +
        "1308\t797\t498\t791\t1312\t99\t1402\t1332\t521\t1354\t1339\t101\t367\t1333\t111\t92\n" +
        "149\t4140\t112\t3748\t148\t815\t4261\t138\t1422\t2670\t32\t334\t2029\t4750\t4472\t2010\n" +
        "114\t605\t94\t136\t96\t167\t553\t395\t164\t159\t284\t104\t530\t551\t544\t18"

class Day2Test : StringSpec() {

    init {
        "should calculate checksum for one row" {
            Day2.part1("5\t1\t9\t5") shouldEqual 8
            Day2.part1("7\t5\t3") shouldEqual 4
            Day2.part1("2\t4\t6\t8") shouldEqual 6
        }

        "should calculate checksum for multiple rows" {
            Day2.part1("5\t1\t9\t5\n" + "7\t5\t3\n" + "2\t4\t6\t8")
        }

        "should confirm solution for part 1" {
            Day2.part1(INPUT) shouldEqual 42378
        }

        "should find evenly divisible numbers for one row" {
            Day2.part2("5\t9\t2\t8") shouldEqual 4
            Day2.part2("9\t4\t7\t3") shouldEqual 3
            Day2.part2("3\t8\t6\t5") shouldEqual 2
        }

        "should sum up division of evenly divisible numbers" {
            Day2.part2("5\t9\t2\t8\n" + "9\t4\t7\t3\n" + "3\t8\t6\t5") shouldEqual 9
        }

        "should confirm solution for part 2" {
            Day2.part2(INPUT) shouldEqual 246
        }
    }
}