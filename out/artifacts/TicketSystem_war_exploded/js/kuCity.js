
(function($) {
    var allCities = ['����|beijing|bj', '�Ϻ�|shanghai|sh', '����|chongqing|cq', '����|shenzhen|sz', '����|guangzhou|gz', '����|hangzhou|hz',
        '�Ͼ�|nanjing|nj', '����|shuzhou|sz', '���|tianjin|tj', '�ɶ�|chengdu|cd', '�ϲ�|nanchang|nc', '����|sanya|sy', '�ൺ|qingdao|qd',
        '����|xiamen|xm', '����|xian|xa', '��ɳ|changsha|cs', '�Ϸ�|hefei|hf', '����|xizang|xz', '���ɹ�|neimenggu|nmg', '����|anqing|aq', '��̩��|ataile|atl', '����|ankang|ak',
        '������|akesu|aks', '��ͷ|baotou|bt', '����|beihai|bh', '��ɫ|baise|bs', '��ɽ|baoshan|bs', '����|changzhi|cz', '����|changchun|cc', '����|changzhou|cz', '����|changdu|cd',
        '����|chaoyang|cy', '����|changde|cd', '����ɽ|changbaishan|cbs', '���|chifeng|cf', '��ͬ|datong|dt', '����|dalian|dl', '����|daxian|dx', '��Ӫ|dongying|dy', '����|daqing|dq', '����|dandong|dd',
        '����|dali|dl', '�ػ�|dunhuang|dh', '������˹|eerduosi|eeds', '��ʩ|enshi|es', '����|fuzhou|fz', '����|fuyang|fy', '����|guiyang|gy',
        '����|guilin|gl', '��Ԫ|guangyuan|gy', '���ľ|geermu|gem', '���ͺ���|huhehaote|hhht', '����|hami|hm',
        '�ں�|heihe|hh', '������|hailaer|hle', '������|haerbin|heb', '����|haikou|hk', '��ɽ|huangshan|hs', '����|handan|hd',
        '����|hanzhong|hz', '����|hetian|ht', '����|jinjiang|jj', '����|jinzhou|jz', '������|jingdezhen|jdz',
        '������|jiayuguan|jyg', '����ɽ|jinggangshan|jgs', '����|jining|jn', '�Ž�|jiujiang|jj', '��ľ˹|jiamusi|jms', '����|jinan|jn',
        '��ʲ|kashi|ks', '����|kunming|km', '����|kangding|kd', '��������|kelamayi|klmy', '�����|kuerle|kel', '�⳵|kuche|kc', '����|lanzhou|lz',
        '����|luoyang|ly', '����|lijiang|lj', '��֥|linzhi|lz', '����|liuzhou|lz', '����|luzhou|lz', '���Ƹ�|lianyungang|lyg', '��ƽ|liping|lp',
        '����|liancheng|lc', '����|lasa|ls', '�ٲ�|lincang|lc', '����|linyi|ly', 'â��|mangshi|ms', 'ĵ����|mudanjiang|mdj', '������|manzhouli|mzl', '����|mianyang|my',
        '÷��|meixian|mx', 'Į��|mohe|mh', '�ϳ�|nanchong|nc', '����|nanning|nn', '����|nanyang|ny', '��ͨ|nantong|nt', '������|nalati|nlt',
        '����|ningbo|nb', '��֦��|panzhihua|pzh', '����|quzhou|qz', '�ػʵ�|qinhuangdao|qhd', '����|qingyang|qy', '�������|qiqihaer|qqhe',
        'ʯ��ׯ|shijiazhuang|sjz', '����|shenyang|sy', '˼é|simao|sm', 'ͭ��|tongren|tr', '����|tacheng|tc', '�ڳ�|tengchong|tc', '̨��|taizhou|tz',
        'ͨ��|tongliao|tl', '̫ԭ|taiyuan|ty', '����|weihai|wh', '����|wuzhou|wz', '��ɽ|wenshan|ws', '����|wuxi|wx', 'Ϋ��|weifang|wf', '����ɽ|wuyishan|wys', '��������|wulanhaote|wlht',
        '����|wenzhou|wz', '��³ľ��|wulumuqi|wlmq', '����|wanzhou|wz', '�ں�|wuhai|wh', '����|xingyi|xy', '����|xichang|xc', '�差|xiangfan|xf',
        '����|xining|xn', '���ֺ���|xilinhaote|xlht', '��˫����|xishuangbanna|xsbn', '����|xuzhou|xz', '����|yiwu|yw', '����|yongzhou|yz', '����|yulin|yl', '�Ӱ�|yanan|ya', '�˳�|yuncheng|yc',
        '��̨|yantai|yt', '����|yinchuan|yc', '�˲�|yichang|yc', '�˱�|yibin|yb', '�γ�|yancheng|yc', '�Ӽ�|yanji|yj', '����|yushu|ys', '����|yining|yn', '�麣|zhuhai|zh', '��ͨ|zhaotong|zt',
        '�żҽ�|zhangjiajie|zjj', '��ɽ|zhoushan|zs', '֣��|zhengzhou|zz', '����|zhongwei|zw', '�ƽ�|zhijiang|zj', 'տ��|zhanjiang|zj'
    ];
    var regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\w+)\|(\w)\w*$/i, // ƥ�人�֣�ƴ��
        regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/, // ֻƥ��ƴ��
        reg_ah = /^[a-h]$/i, // ƥ������ĸΪ a-h
        reg_ip = /^[i-p]/i, // ƥ������ĸΪ i-p
        reg_qz = /^[q-z]/i; // ƥ������ĸΪ q-z

    //�������з���������
    var city = {
        hot: {},
        ABCDEFGH: {},
        IJKLMNOP: {},
        QRSTUVWXYZ: {}
    };

    //���а�����ĸ���࣬��䵽����������
    (function() {
        for (var i = 0, len = allCities.length; i < len; i++) {
            var part = regEx.exec(allCities[i]),
                en = part[1], //������
                letter = part[2], //ƴ��
                spletter = part[3], //ƴ����д
                first = letter[0].toUpperCase(), //ƴ������ĸ
                ltPart; //��ǰ��ĸ�µĳ���

            if (reg_ah.test(first)) {
                ltPart = 'ABCDEFGH';
            } else if (reg_ip.test(first)) {
                ltPart = 'IJKLMNOP';
            } else if (reg_qz.test(first)) {
                ltPart = 'QRSTUVWXYZ';
            }

            city[ltPart][first] ? city[ltPart][first].push(en) : (city[ltPart][first] = [], city[ltPart][first].push(en));

            //����ǰ16������Ϊ���ų���
            if (i < 16) {
                city.hot['hot'] ? city.hot['hot'].push(part[1]) : (city.hot['hot'] = [], city.hot['hot'].push(part[1]));
            }
        }
    })();

    var KuCity = function(target) {
        this.target = target; // �����
        this.container = null; //�������
        this.resultct = null; //�����������
        this.isKeyslect = false; //�Ƿ��������¼�ѡ��
        this.isContainerExit = false; // ��������Ƿ��Ѵ���
    };

    KuCity.prototype = {
        constructor: KuCity,
        //��ʼ��
        init: function() {
            this.creatItem();
            this.tabChange();
            this.citySelect();
            this.inputSearch();
            this.keySelect();
            this.stopPropagation();
        },
        //�������б�
        creatItem: function() {
            if(this.isContainerExit) return;
            var template = '<div class="kucity"><div class="citybox"><h3 class="kucity_header">���ų���(֧�ֺ���/ƴ������)</h3><ul class="kucity_nav"><li class="active">���ų���</li><li>ABCDEFGH</li><li>IJKLMNOP</li><li>QRSTUVWXYZ</li></ul><div class="kucity_body"></div></div><ul class="result"></ul></div>';
            $('body').append(template);

            this.container = $('.kucity');
            this.resultct = $('.result');

            for (var group in city) {
                var itemKey = [];

                for (var item in city[group]) {
                    itemKey.push(item);
                }
                itemKey.sort();
                var itembox = $('<div class="kucity_item">');
                itembox.addClass(group);

                for (var i = 0, iLen = itemKey.length; i < iLen; i++) {

                    var dl = $('<dl>'),
                        dt = '<dt>' + (itemKey[i] == 'hot' ? '' : itemKey[i]) + '</dt>',
                        dd = $('<dd>'),
                        str = '';

                    for (var j = 0, jLen = city[group][itemKey[i]].length; j < jLen; j++) {
                        str += '<span>' + city[group][itemKey[i]][j] + '</span>'
                    }

                    dd.append(str);
                    dl.append(dt).append(dd);
                    itembox.append(dl);
                }
                $('.kucity_body').append(itembox);
                this.container.find('.hot').addClass('active');
            }
            this.isContainerExit = true;
        },
        //������������б�
        creatResult: function(re, value) {
            var result = re.result,
                len = result.length,
                str = '';
            if (!!len) {
                for (var i = 0; i < len; i++) {
                    str += '<li><span class="name">' + result[i].cityName + '</span><span class="letter">' + result[i].py + '</span></li>'
                }
                this.container.find('.result').html('').html(str).find('li').eq(0).addClass('active');
            } else {
                this.container.find('.result').html('<li>û���ҵ�<span class="noresult">' + value + '</span>�����Ϣ</li>');
            }
        },
        //�б��л�
        tabChange: function() {
            $('.kucity_nav').on('click', 'li', function(e) {
                var current = $(e.target),
                    index = current.index();

                current.addClass('active').siblings().removeClass('active');
                $('.kucity_item').eq(index).addClass('active').siblings().removeClass('active');
                $(' .kucity_body').scrollTop(0);

            })
        },
        //����ѡ��
        citySelect: function() {
            var self = this;
            $('.kucity_item dd').on('click', 'span', function(e) {
                self.target.val(($(e.target).text()));
                self.container.hide();
            })
        },
        //���¼�ѡ���������
        keySelect: function() {
            var self = this;
            this.target.on('keydown', function(e){
                var current = self.resultct.find('.active').index();
                if(current !== -1){
                    switch(e.keyCode){
                        //��
                        case 38: 
                            keyActive(false);
                            break;
                        //��
                        case 40:
                            keyActive(true);
                            break;
                        //ȷ��
                        case 13: 
                            self.isKeyslect = false;
                            self.target.val(self.resultct.find('.active .name').text());
                            self.triggleShow('all');
                            self.target.blur();
                            break;
                        default: 
                            self.isKeyslect = false;
                            break;
                    }

                    function keyActive(isInorder) {
                        var max = self.resultct.find('li').length - 1;
                        if(isInorder){
                            current = current == max ? 0 : current + 1;
                        }else{
                            current = current == 0 ? max : current - 1;
                        }
                        self.resultct.find('li').eq(current).addClass('active').siblings().removeClass('active');
                        self.isKeyslect = true;
                    }
                }
            })
        },
        //����
        inputSearch: function() {
            var self = this;
            this.target.on('keyup', function(e) {
                if(!self.isKeyslect){
                    self.throttle(search, this);
                }
            })
            // ���������
            function search(e) {
                var container = self.container;
                self.triggleShow(false);
                var value = $(this).val();
                if (value) {
                    var url = 'https://sjipiao.alitrip.com/city_search.do?_ksTS=1439362066383_11337&lines=10&_input_charset=utf-8&needProvince=true&q=' + value;
                    $.ajax({
                        url: url,
                        type: 'get',
                        dataType: 'jsonp'
                    }).done(function(re) {
                        self.creatResult(re, value);
                    })
                } else {
                    self.triggleShow(true);
                }
            }
        },
        //�б���������� ��ʾ�л�
        triggleShow: function(open) {
            var container = this.container;
            if (open === 'all') {
                container.hide()
            } else if (open) {
                container.find('.citybox').show().end().find('.result').hide();
            } else {
                container.find('.citybox').hide().end().find('.result').show();
            }
        },
        //��������
        throttle: function(fn, context) {
            clearTimeout(fn.tId);
            fn.tId = setTimeout(function(){
                fn.call(context);
            }, 100)
        },
        //��ֹ�¼�ð��
        stopPropagation: function() {
            var self = this;
            //��ֹ�¼�ð��
            this.container.on('click', stopPropagation);
            this.target.on('click', stopPropagation);
            //ҳ���� ����
            $(document).on('click', function() {
                self.container.hide();
            })
            function stopPropagation(e) {
                e.stopPropagation();
            }
        }
    };

    var kucity = null;
    $.fn.kuCity = function(options) {
        var target = $(this);
        target.on('focus', function(e) {
            var top = $(this).offset().top + $(this).outerHeight(),
                left = $(this).offset().left;
            kucity = kucity ? kucity : new KuCity(target);
            kucity.target = $(e.target);
            kucity.init();
            kucity.container.show().offset({
                'top': top + 7,
                'left': left
            });
            kucity.triggleShow(true);
            kucity.resultct.on('click', 'li', function() {
                kucity.target.val($(this).find('.name').text());
                kucity.triggleShow('all');
            })
        })
        return this;
    };
})(jQuery)
