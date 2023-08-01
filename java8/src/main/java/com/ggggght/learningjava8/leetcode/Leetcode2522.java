package com.ggggght.learningjava8.leetcode;

/**
 给你一个字符串 s ，它每一位都是 1 到 9 之间的数字组成，同时给你一个整数 k 。

 如果一个字符串 s 的分割满足以下条件，我们称它是一个 好 分割：


 s 中每个数位 恰好 属于一个子字符串。
 每个子字符串的值都小于等于 k 。


 请你返回 s 所有的 好 分割中，子字符串的 最少 数目。如果不存在 s 的 好 分割，返回 -1 。

 注意：


 一个字符串的 值 是这个字符串对应的整数。比方说，"123" 的值为 123 ，"1" 的值是 1 。
 子字符串 是字符串中一段连续的字符序列。




 示例 1：


 输入：s = "165462", k = 60
 输出：4
 解释：我们将字符串分割成子字符串 "16" ，"54" ，"6" 和 "2" 。每个子字符串的值都小于等于 k = 60 。
 不存在小于 4 个子字符串的好分割。


 示例 2：


 输入：s = "238182", k = 5
 输出：-1
 解释：这个字符串不存在好分割。




 提示：


 1 <= s.length <= 10⁵
 s[i] 是 '1' 到 '9' 之间的数字。
 1 <= k <= 10⁹


 Related Topics 贪心 字符串 动态规划 👍 10 👎 0

 */
public class Leetcode2522 {
    public static void main(String[] args) {

        System.out.println(minimumPartition(
            "521419325436168553487766412663725318568895154574565158763967499615314831839661115487627427375649522286392684179665773123333859911679239221939424156284731635788437121189232651332216348593842542717811612227487182991255817414493431621765391819719375493562884557693766781777533339898173811119356385797788862812186955948129256943189453154228127742177998297633922524646283195499832482373121588663691399282628649312812457175863297244955474736219496259812917128674932498815465937163139296189133158865798184651499892211633523898484472394237168253138161893785852867176266284674792154345986265166758698296964472428474878763637242522282444494225477791784584239348192132788295286889674542446572332868414157922185181774885586758814463854739243599741914342142142145446374589419714943811279999713961878251365341911524576137772366111962554944598146844977394485385319265256414134334931142269452663159666315123999791774964776355839629436999311726841188849847286927781718286825442973925636625497517796379478514589917169532526684527821826539253936474157227746786856375411965472779793218567432455174856537855153982161499556223928844384944813537357157532238897555825633177275628264745574144658992238597513637112389961387972796837512812916159556719935143632628463734912883466728591415462531259149724363911579839125924784995435757616316127592233594132614329141388122167776262626613525993582188533537856993216267621484898425517183717413926832725361684158448663262299547618741626437768164393983511294667311498812489766454527842217929488634356725774359678841257599462655396223621192471267551279839275279174467166735564624287323786655131185812359926486938371823555597825357372449889549552271895889444262519382775242385285559534431168596779928699137125632194972561495235995636329521273191258587982269742992466394525276917549348972843979134853236123865848668844159189815455351768297323489526415989656676154335138414481112521794786317394563924968655493393924472496472171639648239451392857957283588569194445784115728959193986669844319373457639713656187865272985466833286762557677959639432826969962486264811954856243117512765173722349762339312536683792435821972486874633539889412876429159272911313977687142682258813693855843577788447572297568583418321886784241888468626642171528737372887197784112243384844888374432497246496489296227835426675464436577578875198879714534128176157694116229342766536243545269839893651335364985438941576188332521487753141722654446584325667286322431466225494459989176461414564696512799226978157425558273524569251447763539239475332622127256262515533417854473429711678567599621141865364196175718934963939222656541242867924783563682758174587457421695959778936615443171696268813381639368138842414486657821341881634727331929576372535445586446747891523733565634955142269239721816234332576912844967395597629952714936184211631962544161835156852263399515856253743574866311975275524155456172722539464186936838956629919132183554578235754539475737229316443479825531563786344284474994215199743979761544168875746584873162853674436676345895734857745198151232512854233683248662629873835543667768675954327283637542244157635878767146779764956811212135466767534951725578519574415475912745961952496157257386487844496697615158681265657763975762715245329452278371957243518614232861517336562143698467415364697291592227443636973642934873882493655985549874385124524135245592186387433526844912465919191744693155117356323999671129613429927941621499424966689181684758993239549543322617241424497646863881569897354112585945935693765772865767474679732761779225314537455268446235538136533269271994418437791561159274834318111929683188287757636344229577759198737982136971771793618681432175988373657447812132528498124622279212368293853369143434458964712511688848586714693149362646934471716895773725535836323275789538112313724386938351863545571275516367465611652645758622758798862564656631771933473844119293848534934249519988639965831181659421748591775652127899177646165682347136771393237139729519859972131729394416725589915847584886827815352726538399412758133347821474159293762238263413837659979685962417471816236737317763998998277649858554574461439118925631364141353269877793312129849367248393712195615916746158751868328435539567698914991722347585879896736834628187724733948232865589545258262649166352994814137295869317826268498959222544436756311383255925315756213695798625637293598111421628941589952514494328862469461419727649332993677553616166347153144375477115212391112675911729181138474444119868824548555178342287655782246433989611222525839248887292225557886244542219458958614232363967335637657225751228211292214219456981526988175839416566147388992969621254426878654814549269188885265877737477478888536976475422654986855815871298177935692593347161166271728873317363899791628258613134886347476486444733396874255542626971478512289294853799548759714341438285924313361413611151519424197683451317652773135673512632844832139531275536321234151666687441942544454442978489115916346196665615615865371755635251589764395117917693272749765828763669443766445917654631975683286726397932795697538292249866176784781117366467114167919948957786496988943858699814579339852127279991512866858453574489139962936843194982892715574548588655454836735614966619976738845824678695254334122847346862958236162156934164871697611941396657114219837782378548965517295348214166562182978856575555314356867826316544115147855341546682751113465953292743355",
            899964460));
        // System.out.println(minimumPartition("165462",60));
        // System.out.println(minimumPartition("238182", 5));
        // System.out.println(batch(101));
        // System.out.println(batch(99999));
        // System.out.println(batch(5));
    }

    static int batch(int i) {
        int res = 0;
        while (i > 0) {
            i /= 10;
            res++;
        }

        return res;
    }

    public static int minimumPartition(String s, int k) {
        if (s == null || s.length() == 0) return -1;

        // List<String> list = new ArrayList<>();
        int count = 0;
        char[] chars = s.toCharArray();
        boolean reCalc = false;
        long tmp = 0;
        int len = 0;
        int a = k;
        int batch = 0;
        while (a > 0) {
            a /= 10;
            batch++;
        }
        for (int i = 0; i <= chars.length - 1; i++) {
            if (reCalc) {
                reCalc = false;
                tmp = 0;
                len = 0;
            }

            len++;
            tmp = tmp * 10 + (chars[i] - '0');
            if (i == chars.length - 1 && tmp <= k) {
                // list.add(s.substring(i - len + 1));
                count++;
            }
            if (len < batch) continue;
            if (batch == 1 && len == 1 && tmp > k) return -1;

            if (len > batch || tmp > Integer.MAX_VALUE || tmp > k || tmp < 0) {
                reCalc = true;
                count++;
                // list.add(s.substring(i - len + 1, i));
                i--;
            }

        }
        // System.out.println(list);
        // int finalBatch = batch;
        // list.stream().filter(i -> i.length() > finalBatch).forEach(System.out::println);
        return count == 0 ? -1 : count;
    }
}
