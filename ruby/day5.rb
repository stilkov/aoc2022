def get_crates(line)
  result = []
  1.step(line.length, 4) { |c| result << line[c] }

  result
end


def read_file(file)
  lines = File.open(file).readlines
  crates = []
  instructions = []
  header = true
  lines.each do |line|
    header = header && !line.strip.empty?
    if header
      crates << get_crates(line)
    elsif !line.strip.empty? then
      instructions << line
    end
  end
  crates = crates.transpose.map { |elem| elem.filter { |a| a != " " } }
  [crates, instructions]
end

def move(count, from, to)
  count.times { $crates[to - 1].unshift($crates[from - 1].shift) }
end

def process_instruction(line, move_fn)
  count, from, to = line.match(/move (\d+) from (\d+) to (\d+)/).captures.map { |v| v.to_i }
  move_fn.call(count, from, to)
end

$crates, instructions  = read_file "../input/day5.txt"

instructions.each { |i| process_instruction(i, method(:move)) }

result = ""
$crates.each { |c| result << c.first }

puts "Part 1: #{result}"


def move_9001(count, from, to)
  $crates[to - 1].unshift(*$crates[from - 1].shift(count))
end

$crates, instructions  = read_file "../input/day5.txt"

instructions.each { |i| process_instruction(i, method(:move_9001)) }

result = ""
$crates.each { |c| result << c.first }

puts "Part 2: #{result}"
