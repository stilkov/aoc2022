def parse_line(s)
  s.match(/(\d+)-(\d+),(\d+)-(\d+)/).captures.map { |v| v.to_i }
end

def included?(l1, r1, l2, r2)
  (l1 <= l2 && r1 >= r2) || (l2 <= l1 && r2 >= r1)
end

lines = File.open('../input/day4.txt').readlines

def count(lines, filter_fn)
  values = lines.map do |line|
    l1, r1, l2, r2 = parse_line(line)
    filter_fn.call(l1, r1, l2, r2)
  end
end

values = count(lines, method(:included?))

puts "Day 4 Part 1: #{values.count {|v| v == true }}"

def overlapping?(l1, r1, l2, r2)
  l2 <= r1 && l1 <= r2
end

values = count(lines, method(:overlapping?))

puts "Day 4 Part 2: #{values.count {|v| v == true }}"
