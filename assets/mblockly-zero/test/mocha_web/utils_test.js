/**
 * 验证工具函数
 */
describe('utils', function() {
    describe('test data received from bluetooth parser method `decodeData` in MBlockly.Control', function() {

        // 接收到残缺数据
        it('should parse the incomplete data', function() {
            var mock_receive_data = [
                [0xff,0x55,0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d],
                [0x0a]
            ];
            for(var i in mock_receive_data) {
                MBlockly.Control.decodeData(mock_receive_data[i], function(buf) {
                    expect(6).to.eql(buf.length);
                })
            }
        });

        // 接收到完整数据
        it('should parse the complete data', function() {
            var mock_receive_data = [
                [0xff,0x55,0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d,0x0a]
            ];
            for(var i in mock_receive_data) {
                MBlockly.Control.decodeData(mock_receive_data[i], function(buf) {
                    expect(6).to.eql(buf.length);
                })
            }
        });

        // 接收到多余数据
        it('should parse the excess data', function() {
            var mock_receive_data = [
                [0xff,0x55,0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d,0x0a, 0xff, 0x55],
                [0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d,0x0a],
            ];
            for(var i in mock_receive_data) {
                MBlockly.Control.decodeData(mock_receive_data[i], function(buf) {
                    expect(6).to.eql(buf.length);
                })
            }

        });

        // 接收到错误数据
        it('should parse the error data mixed in', function() {
            var mock_receive_data = [
                [0xff,0x55,0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d,0x0a, 0x03, 0x04, 0xff,0x55],
                [0x00,0x02,0xe6,0x9e,0x16,0x41,0x0d,0x0a]
            ];
            for(var i in mock_receive_data) {
                MBlockly.Control.decodeData(mock_receive_data[i], function(buf) {
                    expect(6).to.eql(buf.length);
                })
            }
        });

    });
});

